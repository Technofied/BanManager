package me.confuser.banmanager.bungee.api.events;

import lombok.Getter;
import me.confuser.banmanager.common.data.IpMuteData;

public class IpMutedEvent extends SilentEvent {

  @Getter
  private IpMuteData mute;

  public IpMutedEvent(IpMuteData mute, boolean silent) {
    super(silent);
    this.mute = mute;
  }
}