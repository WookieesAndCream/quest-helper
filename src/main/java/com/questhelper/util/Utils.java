/*
 * Copyright (c) 2023, pajlada <https://github.com/pajlada>
 * Copyright (c) 2023, pajlads <https://github.com/pajlads>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.questhelper.util;

import com.questhelper.domain.AccountType;
import lombok.experimental.UtilityClass;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Varbits;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import net.runelite.api.annotations.Component;
import net.runelite.api.annotations.Interface;

@UtilityClass
public class Utils
{
	/**
	 * Transforms the value from {@link Varbits#ACCOUNT_TYPE} to a convenient enum.
	 *
	 * @param client {@link Client}
	 * @return {@link AccountType}
	 * @apiNote This function should only be called from the client thread.
	 */
	public AccountType getAccountType(@NotNull Client client)
	{
		if (client.getGameState() != GameState.LOGGED_IN) return AccountType.NORMAL;
		return AccountType.get(client.getVarbitValue(Varbits.ACCOUNT_TYPE));
	}

	/**
	 * Pack a widget group ID (Interface) and widget child ID into a widget ID (Component)
	 * @param groupId the {@link Interface}
	 * @param childId the child id
	 * @return the corresponding {@link Component}
	 */
	@Component
	@SuppressWarnings("MagicConstant")
	public int packWidget(@Interface int groupId, int childId) {
		return groupId << 16 | childId;
	}

	/**
	 * Unpack a widget ID (Component) into a widget group ID (Interface) and widget child ID
	 * @param componentId the {@link Component}
	 * @return the corresponding Interface & Child ID
	 */
	@Component
	public Pair<Integer, Integer> unpackWidget(@Component int componentId) {
		return Pair.of(componentId >> 16, componentId & 0xFFFF);
	}

}
