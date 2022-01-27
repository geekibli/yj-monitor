package com.yj.monitor.core.handler;

import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.api.domain.*;
import com.yj.monitor.api.domain.Process;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.NetworkIF;
import oshi.software.os.OSProcess;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/26 上午9:56
 * @Version 1.0
 */
@Component
public class OshiHandler {

    @Resource
    private SystemInfo systemInfo;

    public NetWork getNetWork() {
        NetworkIF[] networkIFs = systemInfo.getHardware().getNetworkIFs();
        if (null == networkIFs || 0 == networkIFs.length) {
            return null;
        }
        NetWork netWork = new NetWork();
        for (NetworkIF networkIF : networkIFs) {
            if (!"en0".equals(networkIF.getName())) {
                continue;
            }
            BeanUtils.copyProperties(networkIF, netWork);
        }
        return netWork;
    }


    public Mem getMemory() {
        SystemInfo systemInfo = new SystemInfo(); // todo
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        if (null != memory) {
            Mem mem = new Mem();
            mem.setAvailable(memory.getAvailable());
            mem.setPageSize(memory.getPageSize());
            mem.setSwapPagesIn(memory.getSwapPagesIn());
            mem.setSwapPagesOut(memory.getSwapPagesOut());
            mem.setSwapTotal(memory.getSwapTotal());
            mem.setSwapUsed(memory.getSwapUsed());
            mem.setTotal(memory.getTotal());
            return mem;
        }
        return null;
    }


    public Processor getProcessor() {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        if (null == processor) {
            return null;
        }

        Processor tar = new Processor();
        tar.setContextSwitches(processor.getContextSwitches());
        tar.setCpu64bit(processor.isCpu64bit());
        tar.setFamily(processor.getFamily());
        tar.setIdentifier(processor.getIdentifier());
        tar.setInterrupts(processor.getInterrupts());
        tar.setLogicalProcessorCount(processor.getLogicalProcessorCount());
        tar.setModel(processor.getModel());
        tar.setName(processor.getName());
        tar.setPhysicalPackageCount(processor.getPhysicalPackageCount());
        tar.setProcessorId(processor.getProcessorID());
        tar.setSystemLoadAverage(processor.getSystemLoadAverage());
        tar.setVendorFreq(processor.getVendorFreq());
        tar.setVendor(processor.getVendor());
        tar.setStepping(processor.getStepping());
        tar.setModel(processor.getModel());
        tar.setPhysicalProcessorCount(processor.getPhysicalProcessorCount());
        tar.setSystemCpuLoadBetweenTicks(processor.getSystemCpuLoadBetweenTicks());
        tar.setSystemUptime(processor.getSystemUptime());
        return tar;
    }

    public OperatingSystem getOperatingSystem() {
        SystemInfo systemInfo = new SystemInfo(); // todo
        oshi.software.os.OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        OperatingSystem os = new OperatingSystem();
        os.setBitness(operatingSystem.getBitness());
        os.setFamily(operatingSystem.getFamily());
        os.setManufacturer(operatingSystem.getManufacturer());
        os.setProcessCount(operatingSystem.getProcessCount());
        os.setThreadCount(operatingSystem.getThreadCount());
        os.setVersion(operatingSystem.getVersion().toString());
        os.setProcessId(operatingSystem.getProcessId());

        OSProcess process = operatingSystem.getProcess(operatingSystem.getProcessId());
        Process pro = new Process();
        BeanUtils.copyProperties(process, pro);
        pro.setState(process.getState().name());
        os.setProcess(pro);
        return os;
    }

    public Cpu getCpu(){
        CpuInfo cpuInfo = OshiUtil.getCpuInfo();
        Cpu cpu = new Cpu();
        BeanUtils.copyProperties(cpuInfo,cpu);
        return cpu;
    }


    public static void main(String[] args) {
        System.err.println(JSON.toJSONString(OshiUtil.getMemory()));
        System.err.println(JSON.toJSONString(new OshiHandler().getMemory()));
    }


}
