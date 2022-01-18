package com.example.demo.reg;

import com.example.demo.common.support.RegularKit;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Test2
 * @Description TODO
 * @Author: zhangsp
 * @date 2021/11/18 15:06
 * @Version 1.0
 */
public class Test2 {

    public static void main(String[] args) {

        Set <String> unitProjIds = new HashSet <>();
        unitProjIds.add("a");
        unitProjIds.add("b");
        unitProjIds.add("c");
        System.out.println(String.join(",", unitProjIds));


        // 工商登记号 正则校验
        if(!RegularKit.match("^[1-8]\\d{14}$", "440682000434706")){
            System.out.println("1." + false);
        }
        // 组织机构代码 正则校验
        if(!RegularKit.match("([0-9A-HJ-NPQRTUWXY]{9}|[0-9A-HJ-NPQRTUWXY]{8}-[0-9A-HJ-NPQRTUWXY])", "67134521-1")){
            System.out.println("2." + false);
        }
        // 统一社会信用代码 正则校验
        if(!RegularKit.match("^([0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}|[1-9]\\d{14})$", "44122319960201171x")){
            System.out.println("3." + false);
        }

    }

}
