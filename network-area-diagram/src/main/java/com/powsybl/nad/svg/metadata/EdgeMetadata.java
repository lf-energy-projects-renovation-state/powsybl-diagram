/**
 * Copyright (c) 2022, RTE (http://www.rte-france.com/)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * SPDX-License-Identifier: MPL-2.0
 */
package com.powsybl.nad.svg.metadata;

import com.powsybl.commons.exceptions.UncheckedXmlStreamException;
import com.powsybl.commons.xml.XmlUtil;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author Luma Zamarreño {@literal <zamarrenolm at aia.es>}
 */
public class EdgeMetadata extends AbstractMetadataItem {

    private static final String ELEMENT_NAME = "edge";
    private static final String NODE1_ATTRIBUTE = "node1";
    private static final String NODE2_ATTRIBUTE = "node2";
    private static final String BUS_NODE1_ATTRIBUTE = "busNode1";
    private static final String BUS_NODE2_ATTRIBUTE = "busNode2";
    private static final String EDGE_TYPE_ATTRIBUTE = "type";

    private final String node1SvgId;
    private final String node2SvgId;
    private final String busNode1SvgId;
    private final String busNode2SvgId;
    private final String edgeType;

    public EdgeMetadata(String svgId, String equipmentId, String node1SvgId, String node2SvgId,
                        String busNode1SvgId, String busNode2SvgId, String edgeType) {
        super(svgId, equipmentId);
        this.node1SvgId = node1SvgId;
        this.node2SvgId = node2SvgId;
        this.busNode1SvgId = busNode1SvgId;
        this.busNode2SvgId = busNode2SvgId;
        this.edgeType = edgeType;
    }

    @Override
    String getElementName() {
        return ELEMENT_NAME;
    }

    @Override
    void write(XMLStreamWriter writer) throws XMLStreamException {
        super.write(writer);
        writer.writeAttribute(NODE1_ATTRIBUTE, node1SvgId);
        writer.writeAttribute(NODE2_ATTRIBUTE, node2SvgId);
        writer.writeAttribute(BUS_NODE1_ATTRIBUTE, busNode1SvgId);
        writer.writeAttribute(BUS_NODE2_ATTRIBUTE, busNode2SvgId);
        writer.writeAttribute(EDGE_TYPE_ATTRIBUTE, edgeType);
    }

    static class Reader implements MetadataItemReader<EdgeMetadata> {
        @Override
        public String getElementName() {
            return ELEMENT_NAME;
        }

        public EdgeMetadata read(XMLStreamReader reader) {
            try {
                String svgId = readDiagramId(reader);
                String equipmentId = readEquipmentId(reader);
                String node1 = reader.getAttributeValue(null, NODE1_ATTRIBUTE);
                String node2 = reader.getAttributeValue(null, NODE2_ATTRIBUTE);
                String busNode1 = reader.getAttributeValue(null, BUS_NODE1_ATTRIBUTE);
                String busNode2 = reader.getAttributeValue(null, BUS_NODE2_ATTRIBUTE);
                String edgeType = reader.getAttributeValue(null, EDGE_TYPE_ATTRIBUTE);
                XmlUtil.readEndElementOrThrow(reader);
                return new EdgeMetadata(svgId, equipmentId, node1, node2, busNode1, busNode2, edgeType);
            } catch (XMLStreamException e) {
                throw new UncheckedXmlStreamException(e);
            }
        }
    }
}
