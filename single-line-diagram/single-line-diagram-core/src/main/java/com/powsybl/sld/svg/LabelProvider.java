/**
 * Copyright (c) 2019, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * SPDX-License-Identifier: MPL-2.0
 */
package com.powsybl.sld.svg;

import com.powsybl.sld.model.coordinate.Direction;
import com.powsybl.sld.model.coordinate.Side;
import com.powsybl.sld.model.graphs.VoltageLevelGraph;
import com.powsybl.sld.model.nodes.BusNode;
import com.powsybl.sld.model.nodes.FeederNode;
import com.powsybl.sld.model.nodes.Node;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Giovanni Ferrari {@literal <giovanni.ferrari at techrain.eu>}
 * @author Franck Lecuyer {@literal <franck.lecuyer at rte-france.com>}
 */
public interface LabelProvider {

    class NodeLabel {
        private final String label;
        private final LabelPosition position;
        private final String userDefinedId;

        public NodeLabel(String label, LabelPosition labelPosition) {
            this(label, labelPosition, null);
        }

        public NodeLabel(String label, LabelPosition labelPosition, String userDefinedId) {
            this.label = label;
            this.position = labelPosition;
            this.userDefinedId = userDefinedId;
        }

        public String getLabel() {
            return label;
        }

        public LabelPosition getPosition() {
            return position;
        }

        public String getUserDefinedId() {
            return userDefinedId;
        }
    }

    class NodeDecorator {
        private final String type;
        private final LabelPosition position;

        /**
         * Creates a node decorator, with given type and position
         * @param type decorator type; corresponds to the type defined in components.json file
         * @param labelPosition position of the decorator relatively to the center of the decorated node
         */
        public NodeDecorator(String type, LabelPosition labelPosition) {
            this.type = type;
            this.position = labelPosition;
        }

        public String getType() {
            return type;
        }

        public LabelPosition getPosition() {
            return position;
        }

    }

    enum LabelDirection {
        OUT, IN;
    }

    List<FeederInfo> getFeederInfos(FeederNode node);

    List<NodeLabel> getNodeLabels(Node node, Direction direction);

    String getTooltip(Node node);

    List<NodeDecorator> getNodeDecorators(Node node, Direction direction);

    List<BusLegendInfo> getBusLegendInfos(VoltageLevelGraph graph);

    Optional<BusInfo> getBusInfo(BusNode node);

    Map<String, Side> getBusInfoSides(VoltageLevelGraph graph);
}
