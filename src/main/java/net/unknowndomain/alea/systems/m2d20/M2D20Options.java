/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unknowndomain.alea.systems.m2d20;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.unknowndomain.alea.systems.RpgSystemOptions;
import net.unknowndomain.alea.systems.annotations.RpgSystemData;
import net.unknowndomain.alea.systems.annotations.RpgSystemOption;


/**
 *
 * @author journeyman
 */
@RpgSystemData(bundleName = "net.unknowndomain.alea.systems.m2d20.RpgSystemBundle")
public class M2D20Options extends RpgSystemOptions
{
    @RpgSystemOption(name = "target", shortcode = "t", description = "2d20.options.targetNumber", argName = "targetNumber")
    private Integer target;
    @RpgSystemOption(name = "focus", shortcode = "f", description = "2d20.options.focus", argName = "focusValue")
    private Integer focus;
    @RpgSystemOption(name = "bonus", shortcode = "b", description = "2d20.options.bonus", argName = "bonusDice")
    private Integer bonus;
//    @RpgSystemOption(name = "reroll", shortcode = "r", description = "7thsea.options.reroll")
//    private boolean reroll;
//    @RpgSystemOption(name = "double", shortcode = "d", description = "7thsea.options.double")
//    private boolean doubleIncrement;
//    @RpgSystemOption(name = "explode", shortcode = "x", description = "7thsea.options.explode")
//    private boolean explode;
//    @RpgSystemOption(name = "increase", shortcode = "i", description = "7thsea.options.increase")
//    private boolean increase;
    
    @Override
    public boolean isValid()
    {
        return !(isHelp() || (target == null));
    }

    public Integer getTarget()
    {
        return target;
    }

    public Integer getFocus()
    {
        return focus;
    }

    public Integer getBonus()
    {
        return bonus;
    }

//    public boolean isReroll()
//    {
//        return reroll;
//    }
//
//    public boolean isDoubleIncrement()
//    {
//        return doubleIncrement;
//    }
//
//    public boolean isExplode()
//    {
//        return explode;
//    }
//
//    public boolean isIncrease()
//    {
//        return increase;
//    }

    public Collection<M2D20Modifiers> getModifiers()
    {
        Set<M2D20Modifiers> mods = new HashSet<>();
        if (isVerbose())
        {
            mods.add(M2D20Modifiers.VERBOSE);
        }
//        if (isReroll())
//        {
//            mods.add(S7thSea2Modifiers.REROLL_LEFTOVER);
//        }
//        if (isDoubleIncrement())
//        {
//            mods.add(S7thSea2Modifiers.DOUBLE_INCREMENT);
//        }
//        if (isExplode())
//        {
//            mods.add(S7thSea2Modifiers.EXPLODING_DICE);
//        }
//        if (isIncrease())
//        {
//            mods.add(S7thSea2Modifiers.INCREASED_DIFFICULTY);
//        }
        return mods;
    }
    
}