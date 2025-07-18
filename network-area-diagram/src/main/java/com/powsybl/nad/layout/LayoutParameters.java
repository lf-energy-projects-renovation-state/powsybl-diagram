/**
 * Copyright (c) 2021-2025, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * SPDX-License-Identifier: MPL-2.0
 */
package com.powsybl.nad.layout;

import com.powsybl.nad.model.Point;

/**
 * @author Florian Dupuy {@literal <florian.dupuy at rte-france.com>}
 */
public class LayoutParameters {
    private boolean textNodesForceLayout = false;
    private Point textNodeFixedShift = new Point(100, -40);
    private int maxSteps = 1000;
    private double textNodeEdgeConnectionYShift = 25;
    private boolean injectionsAdded = false;

    public LayoutParameters() {
    }

    public LayoutParameters(LayoutParameters other) {
        this.textNodesForceLayout = other.textNodesForceLayout;
        this.textNodeFixedShift = new Point(other.textNodeFixedShift.getX(), other.textNodeFixedShift.getY());
        this.maxSteps = other.maxSteps;
        this.textNodeEdgeConnectionYShift = other.textNodeEdgeConnectionYShift;
        this.injectionsAdded = other.injectionsAdded;
    }

    public boolean isTextNodesForceLayout() {
        return textNodesForceLayout;
    }

    public LayoutParameters setTextNodesForceLayout(boolean textNodesForceLayout) {
        this.textNodesForceLayout = textNodesForceLayout;
        return this;
    }

    public Point getTextNodeFixedShift() {
        return textNodeFixedShift;
    }

    public LayoutParameters setTextNodeFixedShift(double textNodeFixedShiftX, double textNodeFixedShiftY) {
        this.textNodeFixedShift = new Point(textNodeFixedShiftX, textNodeFixedShiftY);
        return this;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public LayoutParameters setMaxSteps(int maxSteps) {
        this.maxSteps = maxSteps;
        return this;
    }

    public double getTextNodeEdgeConnectionYShift() {
        return textNodeEdgeConnectionYShift;
    }

    public LayoutParameters setTextNodeEdgeConnectionYShift(double textNodeEdgeConnectionYShift) {
        this.textNodeEdgeConnectionYShift = textNodeEdgeConnectionYShift;
        return this;
    }

    public boolean isInjectionsAdded() {
        return injectionsAdded;
    }

    public LayoutParameters setInjectionsAdded(boolean injectionsAdded) {
        this.injectionsAdded = injectionsAdded;
        return this;
    }
}
