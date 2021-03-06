package com.megacrit.cardcrawl.mod.replay.cards.green;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.mod.replay.actions.unique.PoisonDartsAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import basemod.*;
import basemod.abstracts.*;

public class PoisonDarts
  extends CustomCard
{
  public static final String ID = "Poison Darts";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Poison Darts");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int DEBUFF_AMOUNT = 3;
  private static final int COST = -1;
  
  public PoisonDarts()
  {
    super("Poison Darts", NAME, "cards/replay/poisonDarts.png", -1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
    
	this.baseMagicNumber = 3;
    this.magicNumber = this.baseMagicNumber;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	int upadd = 0;
	if (this.upgraded) {
		//upadd = 1;
	}
	AbstractDungeon.actionManager.addToBottom(new PoisonDartsAction(p, m, this.magicNumber, this.freeToPlayOnce, this.energyOnUse + upadd));
  }
  
  public AbstractCard makeCopy()
  {
    return new PoisonDarts();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(1);
	  //this.exhaust = false;
      //this.rawDescription = UPGRADE_DESCRIPTION;
      //initializeDescription();
    }
  }
}
