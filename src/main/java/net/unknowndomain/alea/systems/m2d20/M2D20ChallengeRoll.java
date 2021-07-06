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
import net.unknowndomain.alea.dice.standard.D6;
import net.unknowndomain.alea.pools.DicePool;
import net.unknowndomain.alea.roll.GenericResult;
import net.unknowndomain.alea.roll.GenericRoll;

/**
 *
 * @author journeyman
 */
public class M2D20ChallengeRoll implements GenericRoll
{
    
    private final DicePool<D6> dicePool;
    private final Set<M2D20Modifiers> mods;
    
    public M2D20ChallengeRoll(Integer diceNumber, M2D20Modifiers ... mod)
    {
        this(diceNumber, Arrays.asList(mod));
    }
    
    public M2D20ChallengeRoll(Integer diceNumber, Collection<M2D20Modifiers> mod)
    {
        
        this.mods = new HashSet<>();
        if (mod != null)
        {
            this.mods.addAll(mod);
        }
        
        this.dicePool = new DicePool<>(D6.INSTANCE, diceNumber);
    }
    
    @Override
    public GenericResult getResult()
    {
        List<Integer> resultsPool = this.dicePool.getResults();
        M2D20ChallengeResults results = new M2D20ChallengeResults(resultsPool);
        for (Integer r : resultsPool)
        {
            if (r <= 2)
            {
                results.addValue(r);
            }
            if (r >= 5)
            {
                results.addOneAndEffect();
            }
        }
        results.setVerbose(mods.contains(M2D20Modifiers.VERBOSE));
        return results;
    }
    
    
}
