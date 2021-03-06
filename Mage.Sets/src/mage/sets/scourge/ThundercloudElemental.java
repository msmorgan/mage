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
package mage.sets.scourge;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.TapAllEffect;
import mage.abilities.effects.common.continuous.LoseAbilityAllEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.filter.Filter;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.ToughnessPredicate;
import mage.filter.predicate.permanent.AnotherPredicate;

/**
 *
 * @author fireshoes
 */
public class ThundercloudElemental extends CardImpl {
    
    private static final FilterCreaturePermanent toughnessFilter = new FilterCreaturePermanent("creatures with toughness 2 or less");
    private static final FilterCreaturePermanent flyingFilter = new FilterCreaturePermanent("All other creatures");

    static {
        toughnessFilter.add(new ToughnessPredicate(Filter.ComparisonType.LessThan, 3));
        flyingFilter.add(new AnotherPredicate());
    }

    public ThundercloudElemental(UUID ownerId) {
        super(ownerId, 54, "Thundercloud Elemental", Rarity.UNCOMMON, new CardType[]{CardType.CREATURE}, "{5}{U}{U}");
        this.expansionSetCode = "SCG";
        this.subtype.add("Elemental");
        this.power = new MageInt(3);
        this.toughness = new MageInt(4);

        // Flying
        this.addAbility(FlyingAbility.getInstance());
        
        // {3}{U}: Tap all creatures with toughness 2 or less.
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new TapAllEffect(toughnessFilter), new ManaCostsImpl("{3}{U}")));
        
        // {3}{U}: All other creatures lose flying until end of turn.
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new LoseAbilityAllEffect(flyingFilter, FlyingAbility.getInstance(), Duration.EndOfTurn), new ManaCostsImpl("{3}{U}")));
    }

    public ThundercloudElemental(final ThundercloudElemental card) {
        super(card);
    }

    @Override
    public ThundercloudElemental copy() {
        return new ThundercloudElemental(this);
    }
}
