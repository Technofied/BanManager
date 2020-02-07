package me.confuser.banmanager.common.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import inet.ipaddr.IPAddress;
import lombok.Getter;
import me.confuser.banmanager.common.storage.mysql.ByteArray;
import me.confuser.banmanager.common.storage.mysql.IpAddress;

@DatabaseTable
public class IpMuteRecord {

  @DatabaseField(generatedId = true)
  @Getter
  private int id;

  @DatabaseField(canBeNull = false, persisterClass = IpAddress.class, columnDefinition = "VARBINARY(16) NOT NULL")
  @Getter
  private IPAddress ip;

  @DatabaseField(canBeNull = false)
  @Getter
  private String reason;

  @DatabaseField(canBeNull = false, columnDefinition = "INT(10) NOT NULL")
  @Getter
  private long expired;

  @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true, persisterClass = ByteArray.class, columnDefinition = "BINARY(16) NOT NULL")
  @Getter
  private PlayerData actor;

  @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true, persisterClass = ByteArray.class, columnDefinition = "BINARY(16) NOT NULL")
  @Getter
  private PlayerData pastActor;

  @DatabaseField(canBeNull = false, columnDefinition = "INT(10) NOT NULL")
  @Getter
  private long pastCreated;

  @DatabaseField(index = true, canBeNull = false, columnDefinition = "INT(10) NOT NULL")
  @Getter
  private long created = System.currentTimeMillis() / 1000L;

  @DatabaseField(canBeNull = false)
  @Getter
  private String createdReason;

  @DatabaseField(index = true)
  @Getter
  private boolean soft = false;

  IpMuteRecord() {

  }

  public IpMuteRecord(IpMuteData mute, PlayerData actor, String reason) {
    ip = mute.getIp();
    expired = mute.getExpires();
    pastActor = mute.getActor();
    pastCreated = mute.getCreated();
    createdReason = reason;
    soft = mute.isSoft();

    this.reason = mute.getReason();
    this.actor = actor;
  }

  public IpMuteRecord(IpMuteData mute, PlayerData actor, long created) {
    ip = mute.getIp();
    reason = mute.getReason();
    expired = mute.getExpires();
    pastActor = mute.getActor();
    pastCreated = mute.getCreated();

    this.actor = actor;
    this.created = created;
  }
}
