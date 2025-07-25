/**
 * Copyright (c) 2023, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * SPDX-License-Identifier: MPL-2.0
 */
package com.powsybl.sld.iidm;

import com.powsybl.diagram.test.Networks;
import com.powsybl.sld.builders.NetworkGraphBuilder;
import com.powsybl.sld.library.FlatDesignLibrary;
import com.powsybl.sld.library.SldResourcesComponentLibrary;
import com.powsybl.sld.model.graphs.VoltageLevelGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Florian Dupuy {@literal <florian.dupuy at rte-france.com>}
 */
class TestComplexParallelLegsInternalPst extends AbstractTestCaseIidm {

    @BeforeEach
    public void setUp() throws IOException {
        network = Networks.createNetworkWithInternalPst();
        graphBuilder = new NetworkGraphBuilder(network);
    }

    @Override
    protected SldResourcesComponentLibrary getResourcesComponentLibrary() {
        return new FlatDesignLibrary();
    }

    @Test
    void test() {
        // build graph
        VoltageLevelGraph g = graphBuilder.buildVoltageLevelGraph("vl1");

        // Run layout
        voltageLevelGraphLayout(g);

        // write SVG and compare to reference
        String filename = "/TestComplexParallelLegsInternalPst.svg";

        assertEquals(toString(filename), toSVG(g, filename, getResourcesComponentLibrary(), layoutParameters, svgParameters, getDefaultDiagramLabelProvider(), getDefaultDiagramStyleProvider()));
    }
}
