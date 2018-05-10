package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zejunweizj on 2017/3/27.
 */
@Controller
@RequestMapping("/hostsSet")
public class HostsSetAction {


    @RequestMapping("query")
    @ResponseBody
    public Object queryHostsSet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

//        HttpSession sessionName = request.getSession();
//        String setting = (String) sessionName.getAttribute("hostsSet");

        String encoding = "UTF-8";
        File hostsSetFile = new File("controller/hostsSetFile.txt");
        if (hostsSetFile.isFile() && hostsSetFile.exists()) {
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(hostsSetFile), encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            String hostsSet = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                System.out.println(lineTxt);
                hostsSet = lineTxt;
            }
            read.close();

            map.put("设定值为:", hostsSet);

        }
        return map;
    }

        @RequestMapping("update")
        @ResponseBody
        public Object updateHostsSet (HttpServletRequest request, HttpServletResponse response) throws Exception {

            Map<String, Object> map = new HashMap<String, Object>();

            String hostsSet = ServletRequestUtils.getRequiredStringParameter(
                    request, "hostsSet");
//
//        HttpSession sessionName = request.getSession();
//        sessionName.setAttribute("hostsSet", hostsSet);

            File hostsSetFile = new File("controller/hostsSetFile.txt");
//            File hostsSetFile = new File("src/main/resources/hostsSetFile.txt");
            if (!hostsSetFile.getParentFile().exists()) {
                hostsSetFile.getParentFile().mkdirs();
                hostsSetFile.createNewFile();
            }
            FileWriter fw = new FileWriter(hostsSetFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(hostsSet);
            bw.close();

            map.put("设定", "success");
            return map;


        }


    }
