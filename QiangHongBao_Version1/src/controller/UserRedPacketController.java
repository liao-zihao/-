package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserRedPacketService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController {
    @Autowired
    private UserRedPacketService userRedPacketService=null;

    @RequestMapping(value="/grabRedPacket")
    @ResponseBody
    public Map<String,Object> grabRedPacket(Long redPacketId,Long userId){
        int result=userRedPacketService.grabRedPacket(redPacketId,userId);
        Map<String,Object> retMap=new HashMap<String,Object>();
        boolean flag=result>0;
        retMap.put("success",flag);
        retMap.put("message",flag?"抢红包成功":"抢红包失败");
        return retMap;
    }
}
