package dao;

import org.springframework.stereotype.Repository;
import pojo.UserRedPacket;

@Repository
public interface UserRedPacketDao {
    public int grapRedPacket(UserRedPacket userRedPacket);
}
