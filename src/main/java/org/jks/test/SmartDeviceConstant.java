package org.jks.test;

/**
 * 智能设备的所有
 *
 * @author liaojian
 * @version 09/23/2016
 */
public enum SmartDeviceConstant{
    SMART_DEVICE_DOOR("Door", "老门"),
    SMART_DEVICE_DOOR_CONTROLLER("DoorController", "密码门"),
    SMART_DEVICE_VENDING_MACHINE("VendingMachine", "售货机"),
    SMART_DEVICE_PM_2_5_CONTROLLER("PM2.5Controller", "空气净化器"),
    SMART_DEVICE_SCREEN("Screen", "云屏幕");
    private String name;
    private String desp;

    SmartDeviceConstant(String name, String desp){
        this.name = name;
        this.desp = desp;
    }

    public String desp(){
        return desp;
    }

    @Override
    public String toString() {
        return name;
    }
}
