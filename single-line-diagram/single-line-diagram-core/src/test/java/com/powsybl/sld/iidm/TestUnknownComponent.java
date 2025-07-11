/**
 * Copyright (c) 2023, RTE (http://www.rte-france.com/)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * SPDX-License-Identifier: MPL-2.0
 */
package com.powsybl.sld.iidm;

import com.powsybl.diagram.test.Networks;
import com.powsybl.sld.builders.NetworkGraphBuilder;
import com.powsybl.sld.library.SldResourcesComponentLibrary;
import com.powsybl.sld.model.graphs.VoltageLevelGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Thomas Adam {@literal <tadam at silicom.fr>}
 */
class TestUnknownComponent extends AbstractTestCaseIidm {

    @BeforeEach
    public void setUp() {
        layoutParameters.setCellWidth(80);

        network = Networks.createTestCase11Network();
        substation = network.getSubstation("subst");
        graphBuilder = new NetworkGraphBuilder(network);
    }

    @Override
    protected SldResourcesComponentLibrary getResourcesComponentLibrary() {
        return new SldResourcesComponentLibrary("unknown", "/UnknownLibrary");
    }

    @Test
    void test() {
        svgParameters.setBusesLegendAdded(true);

        // build voltage level 1 graph
        VoltageLevelGraph g1 = graphBuilder.buildVoltageLevelGraph("vl1");

        voltageLevelGraphLayout(g1);

        // write SVGs and compare to reference
        assertEquals(toString("/TestUnknownLibrary.svg"), toSVG(g1, "/TestUnknownLibrary.svg", getResourcesComponentLibrary(), layoutParameters, svgParameters, getDefaultDiagramLabelProvider(), getDefaultDiagramStyleProvider()));
    }
}
