package com.megacrit.cardcrawl.actions.common;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import beaked.cards.*;
import replayTheSpire.variables.Exhaustive;

import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.ExhaustiveNegationPower;
import com.badlogic.gdx.graphics.*;
import java.util.*;

public class ModifyExhaustiveAction extends AbstractGameAction
{
    private AbstractCard card;
    private boolean bound;
    
    public ModifyExhaustiveAction(final AbstractCard card, final int amount) {
    	this(card, amount, true);
    }
    
    public ModifyExhaustiveAction(final AbstractCard card, final int amount, final boolean bound) {
        this.amount = amount;
        this.card = card;
        this.bound = bound;
    }
    
    public void update() {
    	Exhaustive.ExhaustiveFields.exhaustive.set(this.card, Exhaustive.ExhaustiveFields.exhaustive.get(this.card) + this.amount);
    	if (Exhaustive.ExhaustiveFields.exhaustive.get(this.card) <= 0) {
    		AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this.card, AbstractDungeon.player.hand));
    		AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this.card, AbstractDungeon.player.drawPile));
    		AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this.card, AbstractDungeon.player.discardPile));
    	}
    	if (this.bound && Exhaustive.ExhaustiveFields.exhaustive.get(this.card) > Exhaustive.ExhaustiveFields.baseExhaustive.get(this.card)) {
    		Exhaustive.ExhaustiveFields.exhaustive.set(this.card, Exhaustive.ExhaustiveFields.baseExhaustive.get(this.card));
    	}
        this.card.applyPowers();
        this.card.initializeDescription();
        this.isDone = true;
    }
}