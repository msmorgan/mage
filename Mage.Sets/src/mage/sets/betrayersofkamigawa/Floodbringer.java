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
package mage.sets.betrayersofkamigawa;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.ReturnToHandChosenControlledPermanentCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.TapTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.filter.common.FilterControlledLandPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.target.common.TargetControlledPermanent;
import mage.target.common.TargetLandPermanent;

/**
 *
 * @author Loki
 */
public class Floodbringer extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledLandPermanent("a land you control (return to hand)");

    public Floodbringer(UUID ownerId) {
        super(ownerId, 34, "Floodbringer", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{1}{U}");
        this.expansionSetCode = "BOK";
        this.subtype.add("Moonfolk");
        this.subtype.add("Wizard");

        this.power = new MageInt(1);
        this.toughness = new MageInt(2);
        this.addAbility(FlyingAbility.getInstance());

        // {2}, Return a land you control to its owner's hand: Tap target land.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new TapTargetEffect(), new GenericManaCost(2));
        ReturnToHandChosenControlledPermanentCost cost = new ReturnToHandChosenControlledPermanentCost(new TargetControlledPermanent(filter));
        cost.setText("Return a land you control to its owner's hand");
        ability.addCost(cost);
        ability.addTarget(new TargetLandPermanent());
        this.addAbility(ability);
    }

    public Floodbringer(final Floodbringer card) {
        super(card);
    }

    @Override
    public Floodbringer copy() {
        return new Floodbringer(this);
    }
}
