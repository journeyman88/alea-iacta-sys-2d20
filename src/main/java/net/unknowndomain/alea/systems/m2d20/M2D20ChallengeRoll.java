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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.random.dice.DicePool;
import net.unknowndomain.alea.random.dice.bag.D6;
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
    private final Locale lang;
    
    public M2D20ChallengeRoll(Locale lang, Integer diceNumber, M2D20Modifiers ... mod)
    {
        this(lang, diceNumber, Arrays.asList(mod));
    }
    
    public M2D20ChallengeRoll(Locale lang, Integer diceNumber, Collection<M2D20Modifiers> mod)
    {
        
        this.mods = new HashSet<>();
        if (mod != null)
        {
            this.mods.addAll(mod);
        }
        
        this.dicePool = new DicePool<>(D6.INSTANCE, diceNumber);
        this.lang = lang;
    }
    
    @Override
    public GenericResult getResult()
    {
        List<SingleResult<Integer>> resultsPool = this.dicePool.getResults();
        M2D20ChallengeResults results = new M2D20ChallengeResults(resultsPool);
        for (SingleResult<Integer> r : resultsPool)
        {
            if (r.getValue() <= 2)
            {
                results.addValue(r.getValue());
            }
            if (r.getValue() >= 5)
            {
                results.addOneAndEffect();
            }
        }
        results.setVerbose(mods.contains(M2D20Modifiers.VERBOSE));
        results.setLang(lang);
        return results;
    }
    
    
}
