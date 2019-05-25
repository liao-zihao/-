package service;

import dao.RedPacketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.RedPacket;

public class RedPacketServiceImpl implements RedPacketService {
    @Autowired
    private RedPacketDao redPacketDao=null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public RedPacket getRedPacket(Long id) {
        return redPacketDao.getRedPacket(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int decreaseRedPacket(Long id) {
        return redPacketDao.decreaseRedPacket(id);
    }
}
