/**
 * Copyright (c) 2024, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * SPDX-License-Identifier: MPL-2.0
 */
package com.powsybl.nad.model;

import java.util.List;

/**
 * @author Florian Dupuy {@literal <florian.dupuy at rte-france.com>}
 */
public class Injection extends AbstractIdentifiable {

    public enum Type {
        LOAD,
        SHUNT_COMPENSATOR_CAPACITOR,
        SHUNT_COMPENSATOR_INDUCTOR,
        STATIC_VAR_COMPENSATOR,
        GENERATOR,
        BATTERY
    }

    private final Type type;
    private double angle;
    private Point injectionPoint;
    private Point busNodePoint;
    private Point arrowPoint;

    public Injection(String diagramId, String equipmentId, String nameOrId, Type type) {
        super(diagramId, equipmentId, nameOrId);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getComponentType() {
        return type.name();
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setEdge(Point busNodePoint, Point injPoint) {
        this.busNodePoint = busNodePoint;
        this.injectionPoint = injPoint;
    }

    public List<Point> getEdge() {
        return List.of(busNodePoint, injectionPoint);
    }

    public Point getBusNodePoint() {
        return busNodePoint;
    }

    public Point getInjectionPoint() {
        return injectionPoint;
    }

    public Point getArrowPoint() {
        return arrowPoint;
    }

    public void setArrowPoint(Point arrowPoint) {
        this.arrowPoint = arrowPoint;
    }

    public Point getIconOrigin(double circleRadius) {
        double edgeDistance = busNodePoint.distance(injectionPoint);
        Point circleCenter = busNodePoint.atDistance(edgeDistance + circleRadius, injectionPoint);
        return circleCenter.shift(-circleRadius, -circleRadius);
    }
}
