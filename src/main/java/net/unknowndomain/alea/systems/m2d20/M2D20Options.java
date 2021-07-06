/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unknowndomain.alea.systems.m2d20;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
    @RpgSystemOption(name = "old", shortcode = "o", description = "2d20.options.oldMode")
    private boolean oldMode;
    @RpgSystemOption(name = "assistant", shortcode = "a", description = "2d20.options.assistant", argName = "assistantTarget")
    private List<String> assistants;
    @RpgSystemOption(name = "determination", shortcode = "d", description = "2d20.options.determination", argName = "determinationSpent")
    private Integer determination;
    @RpgSystemOption(name = "challenge", shortcode = "c", description = "2d20.options.challenge")
    private Integer challenge;
//    @RpgSystemOption(name = "explode", shortcode = "x", description = "7thsea.options.explode")
//    private boolean explode;
    
    @Override
    public boolean isValid()
    {
        return !(isHelp() || !(isBasic() ^ isChallenge()));
    }
    
    public boolean isBasic()
    {
        return (target != null);
    }
    
    public boolean isChallenge()
    {
        return (challenge != null);
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

    public boolean isOldMode()
    {
        return oldMode;
    }

    public Collection<M2D20Modifiers> getModifiers()
    {
        Set<M2D20Modifiers> mods = new HashSet<>();
        if (isVerbose())
        {
            mods.add(M2D20Modifiers.VERBOSE);
        }
        if (isOldMode())
        {
            mods.add(M2D20Modifiers.ONE_NOT_AUTOCRIT);
        }
        return mods;
    }

    public List<String> getAssistants()
    {
        return assistants;
    }

    public void setAssistants(List<String> assistants)
    {
        this.assistants = assistants;
    }

    public Integer getDetermination()
    {
        return determination;
    }

    public void setDetermination(Integer determination)
    {
        this.determination = determination;
    }
    
    public List<Integer> parseAssistants()
    {
        List<Integer> ass = new ArrayList<>(assistants.size());
        for (String a : assistants)
        {
            ass.add(Integer.parseInt(a));
        }
        return ass;
    }

    public Integer getChallenge()
    {
        return challenge;
    }

    public void setChallenge(Integer challenge)
    {
        this.challenge = challenge;
    }
    
}