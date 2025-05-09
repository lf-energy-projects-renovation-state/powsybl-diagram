/**
 * Copyright (c) 2021, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.powsybl.nad.svg;

import com.powsybl.ieeecdf.converter.IeeeCdfNetworkFactory;
import com.powsybl.iidm.network.Network;
import com.powsybl.nad.AbstractTest;
import com.powsybl.nad.build.iidm.VoltageLevelFilter;
import com.powsybl.nad.layout.LayoutParameters;
import com.powsybl.nad.svg.iidm.DefaultLabelProvider;
import com.powsybl.nad.svg.iidm.TopologicalStyleProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Florian Dupuy {@literal <florian.dupuy at rte-france.com>}
 */
class TopologicalStyleTest extends AbstractTest {

    @BeforeEach
    void setup() {
        setLayoutParameters(new LayoutParameters());
        setSvgParameters(new SvgParameters()
                .setInsertNameDesc(true)
                .setSvgWidthAndHeightAdded(true)
                .setFixedWidth(800));
    }

    @Override
    protected StyleProvider getStyleProvider(Network network) {
        return new TopologicalStyleProvider(network);
    }

    @Override
    protected LabelProvider getLabelProvider(Network network) {
        return new DefaultLabelProvider(network, getSvgParameters());
    }

    @Test
    void testIEEE57() {
        Network network = IeeeCdfNetworkFactory.create57();
        assertSvgEquals("/IEEE_57_bus.svg", network);
    }

    @Test
    void testIEEE118() {
        Network network = IeeeCdfNetworkFactory.create118();
        assertSvgEquals("/IEEE_118_bus.svg", network);
    }

    @Test
    void testIEEE118PartialGraph() {
        Network network = IeeeCdfNetworkFactory.create118();
        VoltageLevelFilter vlDepthFilter = VoltageLevelFilter.createVoltageLevelDepthFilter(network, "VL54", 2);
        assertSvgEquals("/IEEE_118_bus_partial.svg", network, vlDepthFilter);
    }

    @Test
    void testIEEE118PartialNonConnectedGraph() {
        Network network = IeeeCdfNetworkFactory.create118();
        VoltageLevelFilter vlDepthFilter = VoltageLevelFilter.createVoltageLevelsDepthFilter(network, Arrays.asList("VL32", "VL38"), 1);
        assertSvgEquals("/IEEE_118_bus_partial_non_connected.svg", network, vlDepthFilter);
    }

    @Test
    void testEurope() {
        Network network = Network.read("simple-eu.uct", getClass().getResourceAsStream("/simple-eu.uct"));
        assertSvgEquals("/simple-eu.svg", network);
    }

    @Test
    void testEuropeLoopAperture80() {
        Network network = Network.read("simple-eu.uct", getClass().getResourceAsStream("/simple-eu.uct"));
        getSvgParameters().setLoopEdgesAperture(80);
        assertSvgEquals("/simple-eu-loop80.svg", network);
    }

    @Test
    void testEuropeLoopAperture100() {
        Network network = Network.read("simple-eu.uct", getClass().getResourceAsStream("/simple-eu.uct"));
        getSvgParameters().setLoopEdgesAperture(100);
        assertSvgEquals("/simple-eu-loop100.svg", network);
    }
}
