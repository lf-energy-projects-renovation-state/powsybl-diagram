/**
 * Copyright (c) 2021, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * SPDX-License-Identifier: MPL-2.0
 */
package com.powsybl.sld.iidm;

import com.powsybl.diagram.test.Networks;
import com.powsybl.sld.builders.NetworkGraphBuilder;
import com.powsybl.sld.layout.PositionVoltageLevelLayoutFactory;
import com.powsybl.sld.layout.PositionVoltageLevelLayoutFactoryParameters;
import com.powsybl.sld.layout.VerticalSubstationLayoutFactory;
import com.powsybl.sld.layout.position.predefined.PositionPredefined;
import com.powsybl.sld.model.graphs.SubstationGraph;
import com.powsybl.sld.model.graphs.VoltageLevelGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Slimane Amar {@literal <slimane.amar at rte-france.com>}
 */
class TestInternalBranchesNodeBreaker extends AbstractTestCaseIidm {

    @BeforeEach
    public void setUp() {
        network = Networks.createNodeBreakerNetworkWithInternalBranches("TestInternalBranchesNodeBreaker", "test");
        graphBuilder = new NetworkGraphBuilder(network);
        substation = network.getSubstation("S1");
    }

    @Test
    void testVLGraph() {
        // build graph
        VoltageLevelGraph g = graphBuilder.buildVoltageLevelGraph(network.getVoltageLevel("VL1").getId());

        // Run layout with default parameters and compare subsequent SVG with reference
        voltageLevelGraphLayout(g);
        assertEquals(toString("/InternalBranchesNodeBreaker.svg"),
                toSVG(g, "/InternalBranchesNodeBreaker.svg", componentLibrary, layoutParameters, svgParameters, getDefaultDiagramLabelProvider(), getDefaultDiagramStyleProvider()));
    }

    @Test
    void testVLGraphExternal2WT() {
        // build graph
        VoltageLevelGraph g = graphBuilder.buildVoltageLevelGraph(network.getVoltageLevel("VL1").getId());

        // Run layout with specific parameters and compare subsequent SVG with reference
        PositionVoltageLevelLayoutFactoryParameters pvllfParameters = new PositionVoltageLevelLayoutFactoryParameters()
                .setSubstituteInternalMiddle2wtByEquipmentNodes(false);
        new PositionVoltageLevelLayoutFactory(new PositionPredefined(), pvllfParameters).create(g).run(this.layoutParameters);
        assertEquals(toString("/InternalBranchesNodeBreaker_externalPst.svg"),
                toSVG(g, "/InternalBranchesNodeBreaker_externalPst.svg", componentLibrary, layoutParameters, svgParameters, getDefaultDiagramLabelProvider(), getDefaultDiagramStyleProvider()));
    }

    @Test
    void testSubstationGraphH() {
        // build substation graph
        SubstationGraph g = graphBuilder.buildSubstationGraph(substation.getId());

        // Run horizontal substation layout
        substationGraphLayout(g);

        assertEquals(toString("/InternalBranchesNodeBreakerH.json"), toJson(g, "/InternalBranchesNodeBreakerH.json"));
    }

    @Test
    void testSubstationGraphV() {
        // build substation graph
        SubstationGraph g = graphBuilder.buildSubstationGraph(substation.getId());

        // Run vertical substation layout
        new VerticalSubstationLayoutFactory().create(g, new PositionVoltageLevelLayoutFactory()).run(layoutParameters);

        assertEquals(toString("/InternalBranchesNodeBreakerV.json"), toJson(g, "/InternalBranchesNodeBreakerV.json"));
    }
}
