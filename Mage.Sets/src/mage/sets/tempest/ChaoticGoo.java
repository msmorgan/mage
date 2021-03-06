/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.tempest;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.effects.common.counter.RemoveCounterSourceEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.constants.TargetController;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;

/**
 *
 * @author fireshoes
 */
public class ChaoticGoo extends CardImpl {

    public ChaoticGoo(UUID ownerId) {
        super(ownerId, 168, "Chaotic Goo", Rarity.RARE, new CardType[]{CardType.CREATURE}, "{2}{R}{R}");
        this.expansionSetCode = "TMP";
        this.subtype.add("Ooze");
        this.power = new MageInt(0);
        this.toughness = new MageInt(0);

        // Chaotic Goo enters the battlefield with three +1/+1 counters on it.
        this.addAbility(new EntersBattlefieldAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance(3)),
            "{this} enters the battlefield with three +1/+1 counters on it"));
        
        // At the beginning of your upkeep, you may flip a coin. If you win the flip, put a +1/+1 counter on Chaotic Goo. If you lose the flip, remove a +1/+1 counter from Chaotic Goo.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(Zone.BATTLEFIELD, new ChaoticGooEffect(), TargetController.YOU, true));
    }

    public ChaoticGoo(final ChaoticGoo card) {
        super(card);
    }

    @Override
    public ChaoticGoo copy() {
        return new ChaoticGoo(this);
    }
}

class ChaoticGooEffect extends OneShotEffect {

    public ChaoticGooEffect() {
        super(Outcome.Damage);
        staticText = "flip a coin. If you win the flip, put a +1/+1 counter on {this}. If you lose the flip, remove a +1/+1 counter from {this}";
    }

    public ChaoticGooEffect(ChaoticGooEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Permanent permanent = game.getPermanent(source.getSourceId());
        if (controller != null && permanent != null) {
            if (controller.flipCoin(game)) {
                game.informPlayers("Chaotic Goo: Won flip. Put a +1/+1 counter on Chaotic Goo.");
                new AddCountersSourceEffect(CounterType.P1P1.createInstance(1)).apply(game, source);
                return true;
            } else {
                game.informPlayers("Chaotic Goo: Lost flip. Remove a +1/+1 counter on Chaotic Goo.");
                new RemoveCounterSourceEffect(CounterType.P1P1.createInstance(1)).apply(game, source);
                return true;
                }
            }
        return false;
    }

    @Override
    public ChaoticGooEffect copy() {
        return new ChaoticGooEffect(this);
    }
}
