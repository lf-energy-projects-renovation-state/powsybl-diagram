/**
 * Copyright (c) 2019, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.powsybl.sld.color;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Massimo Ferraro <massimo.ferraro@techrain.eu>
 */
public class BaseVoltagesConfigTest {

    @Test
    public void test() {
        Yaml yaml = new Yaml(new Constructor(BaseVoltagesConfig.class));
        InputStream configInputStream = getClass().getResourceAsStream("/base-voltages.yml");
        BaseVoltagesConfig config = yaml.load(configInputStream);
        assertNotNull(config);
        assertNotNull(config.getBaseVoltages());
        assertEquals(8, config.getBaseVoltages().size());
        assertEquals("400", config.getBaseVoltages().get(1).getName());
        assertEquals(300, config.getBaseVoltages().get(1).getMinValue(), 0);
        assertEquals(500, config.getBaseVoltages().get(1).getMaxValue(), 0);
        assertEquals("#ff0000", config.getBaseVoltages().get(1).getColor());
        assertEquals("Default", config.getBaseVoltages().get(1).getProfile());
        assertEquals("225", config.getBaseVoltages().get(2).getName());
        assertEquals(180, config.getBaseVoltages().get(2).getMinValue(), 0);
        assertEquals(300, config.getBaseVoltages().get(2).getMaxValue(), 0);
        assertEquals("#228b22", config.getBaseVoltages().get(2).getColor());
        assertEquals("Default", config.getBaseVoltages().get(2).getProfile());
        assertEquals("Default", config.getDefaultProfile());
    }

}
