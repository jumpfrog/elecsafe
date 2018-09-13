package com.holley.elecsafe.protocol;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holley.elecsafe.channel.IProtocol;
import com.holley.elecsafe.service.BaseService;
import com.holley.elecsafe.service.DeviceManagerService;
import com.holley.platform.common.util.StringUtil;

public class ProtocolService extends BaseService {

    private String                    protocolList;
    // 暂时没用到，一条通道初始化一个协议实例
    private HashMap<Short, IProtocol> protocolMap = null;

    static Log                        logger      = LogFactory.getLog(ProtocolService.class.getName());

    public synchronized IProtocol getProtocol(Short id, DeviceManagerService deviceManagerService) {
        if (id == null) {
            return null;
        }
        IProtocol protocol = null;

        if (protocolList != null && protocolList.length() > 0) {
            protocolList = StringUtil.replaceBlank(protocolList);
            String protocols[] = protocolList.split(",");
            if (protocols == null) {
                return null;
            }
            for (int i = 0; i < protocols.length; i++) {
                String ids[] = protocols[i].split(":");
                if (ids == null || ids.length != 2) {
                    continue;
                }
                /**
                 * 循环直到找到对应的协议类
                 */
                if (!id.equals(Short.parseShort(ids[0]))) {
                    continue;
                }
                try {
                    Class c = Class.forName(ids[1]);
                    Constructor c1 = c.getDeclaredConstructor(new Class[] { DeviceManagerService.class });
                    c1.setAccessible(true);
                    protocol = (IProtocol) c1.newInstance(deviceManagerService);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    logger.error(e.getStackTrace());
                    return null;
                }
                return protocol;
            }
        }
        return null;
    }

    public String getProtocolList() {
        return protocolList;
    }

    public void setProtocolList(String protocolList) {
        this.protocolList = protocolList;
    }
}
