/*
 * Copyright 2020 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unknowndomain.alea.systems.m2d20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.unknowndomain.alea.dice.standard.D20;
import net.unknowndomain.alea.pools.DicePool;
import net.unknowndomain.alea.roll.GenericResult;
import net.unknowndomain.alea.roll.GenericRoll;

/**
 *
 * @author journeyman
 */
public class M2D20Roll implements GenericRoll
{
    
    private final DicePool<D20> dicePool;
    private final Set<M2D20Modifiers> mods;
    private final int targetNumber;
    private final int focus;
    private final int determination;
    private final List<Integer> assistants;
    
    public M2D20Roll(Integer targetNumber, Integer focus, Integer bonus, Integer determination, List<Integer> assistants, M2D20Modifiers ... mod)
    {
        this(targetNumber, focus, bonus, determination, assistants, Arrays.asList(mod));
    }
    
    public M2D20Roll(Integer targetNumber, Integer focus, Integer bonus, Integer determination, List<Integer> assistants, Collection<M2D20Modifiers> mod)
    {
        
        this.mods = new HashSet<>();
        if (mod != null)
        {
            this.mods.addAll(mod);
        }
        int dice = 2;
        if (bonus != null)
        {
            int b = bonus;
            if (b < 0)
            {
                b = 0;
            }
            if (b > 3)
            {
                b = 3;
            }
            dice += b;
        }
        int d = 0;
        if (determination != null)
        {
            d = determination;
            if (d < 0)
            {
                d = 0;
            }
            if (d > 3)
            {
                d = 3;
            }
            dice -= d;
        }
        this.determination = d;
        int f = 1;
        if (mods.contains(M2D20Modifiers.ONE_NOT_AUTOCRIT))
        {
            f = 0;
        }
        if (focus != null)
        {
            f = focus;
        }
        this.dicePool = new DicePool<>(D20.INSTANCE, dice);
        this.focus = f;
        this.targetNumber = targetNumber;
        this.assistants = assistants;
    }
    
    @Override
    public GenericResult getResult()
    {
        List<Integer> resultsPool = this.dicePool.getResults();
        for(int i = 0; i< determination; i++)
        {
            resultsPool.add(0,1);
        }
        M2D20Results results = new M2D20Results(resultsPool);
        for (Integer r : resultsPool)
        {
            if ((r <= targetNumber) && ( r > focus))
            {
                results.addSuccess(r);
            }
            if ((r <= targetNumber) && ( r <= focus))
            {
                results.addCriticalSuccess(r);
            }
            if (r == 20)
            {
                results.addComplication();
            }
            
        }
        if ((assistants != null) && (results.getSuccesses() > 0))
        {
            for (Integer ta : assistants)
            {
                int r = D20.INSTANCE.roll();
                results.getAssistDice().add(r);
                if (r <= ta)
                {
                    results.addSuccess(r);
                }
                if (r == 20)
                {
                    results.addComplication();
                }
            }
        }
        results.setVerbose(mods.contains(M2D20Modifiers.VERBOSE));
        return results;
    }
    
    
}
