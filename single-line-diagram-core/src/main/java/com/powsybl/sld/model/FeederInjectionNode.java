/**
 * Copyright (c) 2020, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.powsybl.sld.model;

import com.powsybl.sld.library.ComponentTypeName;

/**
 * @author Geoffroy Jamgotchian <geoffroy.jamgotchian at rte-france.com>
 */
public class FeederInjectionNode extends FeederNode {

    protected FeederInjectionNode(String id, String name, String componentType, Graph graph) {
        super(id, name, id, componentType, graph, FeederType.INJECTION);
    }

    public static FeederInjectionNode createGenerator(Graph graph, String id, String name) {
        return new FeederInjectionNode(id, name, ComponentTypeName.GENERATOR, graph);
    }

    public static FeederInjectionNode createLoad(Graph graph, String id, String name) {
        return new FeederInjectionNode(id, name, ComponentTypeName.LOAD, graph);
    }

    public static FeederInjectionNode createVscConverterStation(Graph graph, String id, String name) {
        return new FeederInjectionNode(id, name, ComponentTypeName.VSC_CONVERTER_STATION, graph);
    }

    public static FeederInjectionNode createStaticVarCompensator(Graph graph, String id, String name) {
        return new FeederInjectionNode(id, name, ComponentTypeName.STATIC_VAR_COMPENSATOR, graph);
    }

    public static FeederInjectionNode createInductor(Graph graph, String id, String name) {
        return new FeederInjectionNode(id, name, ComponentTypeName.INDUCTOR, graph);
    }

    public static FeederInjectionNode createCapacitor(Graph graph, String id, String name) {
        return new FeederInjectionNode(id, name, ComponentTypeName.CAPACITOR, graph);
    }

    public static FeederInjectionNode createDanglingLine(Graph graph, String id, String name) {
        return new FeederInjectionNode(id, name, ComponentTypeName.DANGLING_LINE, graph);
    }
}
