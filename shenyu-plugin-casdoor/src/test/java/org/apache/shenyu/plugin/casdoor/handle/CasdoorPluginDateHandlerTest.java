package org.apache.shenyu.plugin.casdoor.handle;

import org.apache.shenyu.common.dto.PluginData;
import org.apache.shenyu.common.enums.PluginEnum;
import org.apache.shenyu.common.utils.GsonUtils;
import org.apache.shenyu.common.utils.Singleton;
import org.casbin.casdoor.config.CasdoorConfig;
import org.casbin.casdoor.service.CasdoorAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CasdoorPluginDateHandlerTest {

    private CasdoorPluginDateHandler casdoorPluginDateHandlerTest;

    @BeforeEach
    public void setup(){
        casdoorPluginDateHandlerTest = new CasdoorPluginDateHandler();
    }

    @Test
    void handlerPlugin() {
        final PluginData pluginData = new PluginData("pluginId", "pluginName", "{\"endpoint\":\"http://localhost:8000\"}", "0", false);
        casdoorPluginDateHandlerTest.handlerPlugin(pluginData);
        CasdoorAuthService casdoorAuthService = Singleton.INST.get(CasdoorAuthService.class);
        Map<String, String> map = GsonUtils.getInstance().toObjectMap(pluginData.getConfig(), String.class);
        assertEquals(casdoorAuthService, map.get("endpoint"));
    }

    @Test
    public void testPluginNamed() {
        final String result = casdoorPluginDateHandlerTest.pluginNamed();
        assertEquals(PluginEnum.CASDOOR.getName(), result);
    }
}