/*
 * This file is part of packetevents - https://github.com/retrooper/packetevents
 * Copyright (C) 2021 retrooper and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.retrooper.packetevents.wrapper.play.server;

import com.github.retrooper.packetevents.event.impl.PacketSendEvent;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class WrapperPlayServerSetExperience extends PacketWrapper<WrapperPlayServerSetExperience> {
    private float experienceBar;
    private int level;
    private int totalExperience;

    public WrapperPlayServerSetExperience(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerSetExperience() {
        super(PacketType.Play.Server.SET_EXPERIENCE);
    }

    @Override
    public void readData() {
        experienceBar = readFloat();
        if (serverVersion == ServerVersion.v_1_7_10) {
            level = readShort();
            totalExperience = readShort();
        }
        else {
            level = readVarInt();
            totalExperience = readVarInt();
        }
    }

    @Override
    public void readData(WrapperPlayServerSetExperience wrapper) {
        experienceBar = wrapper.experienceBar;
        level = wrapper.level;
        totalExperience = wrapper.totalExperience;
    }

    @Override
    public void writeData() {
        writeFloat(experienceBar);
        if (serverVersion == ServerVersion.v_1_7_10) {
            writeShort(level);
            writeShort(totalExperience);
        }
        else {
            writeVarInt(level);
            writeVarInt(totalExperience);
        }
    }

    public float getExperienceBar() {
        return experienceBar;
    }

    public void setExperienceBar(float experienceBar) {
        this.experienceBar = experienceBar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }
}
