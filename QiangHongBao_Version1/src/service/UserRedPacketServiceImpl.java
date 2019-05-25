package service;

import dao.RedPacketDao;
import dao.UserRedPacketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.RedPacket;
import pojo.UserRedPacket;

public class UserRedPacketServiceImpl implements UserRedPacketService {
    @Autowired
    private UserRedPacketDao userRedPacketDao=null;

    @Autowired
    private RedPacketDao redPacketDao=null;

    private static final int FAILED=0;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int grabRedPacket(Long redPacketId, Long userId) {
        RedPacket redPacket=redPacketDao.getRedPacket(redPacketId);
        if(redPacket.getStock()>0){
            redPacketDao.decreaseRedPacket(redPacketId);
            UserRedPacket userRedPacket=new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(redPacketId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("grab redPacket"+redPacketId);

            int result=userRedPacketDao.grapRedPacket(userRedPacket);
            return result;
        }
        return FAILED;
    }
}
