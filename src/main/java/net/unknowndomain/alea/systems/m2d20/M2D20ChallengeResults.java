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
import java.util.Collections;
import java.util.List;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.LocalizedResult;

/**
 *
 * @author journeyman
 */
public class M2D20ChallengeResults extends LocalizedResult
{
    
    private static final String BUNDLE_NAME = "net.unknowndomain.alea.systems.m2d20.RpgSystemBundle";
    
    private final List<SingleResult<Integer>> results;
    private int value = 0;
    private int effects = 0;
    private M2D20ChallengeResults prev;
    
    public M2D20ChallengeResults(List<SingleResult<Integer>> results)
    {
        List<SingleResult<Integer>> tmp = new ArrayList<>(results.size());
        tmp.addAll(results);
        this.results = Collections.unmodifiableList(tmp);
    }
    
    public void addValue(int value)
    {
        this.value +=value;
    }
    
    public void addOneAndEffect()
    {
        value++;
        effects++;
    }
    public int getValue()
    {
        return value;
    }

    public List<SingleResult<Integer>> getResults()
    {
        return results;
    }

    public M2D20ChallengeResults getPrev()
    {
        return prev;
    }

    public void setPrev(M2D20ChallengeResults prev)
    {
        this.prev = prev;
    }
    
    @Override
    protected void formatResults(MsgBuilder messageBuilder, boolean verbose, int indentValue)
    {
        String indent = getIndent(indentValue);
        messageBuilder.append(indent).append(translate("2d20.results.total", getValue())).appendNewLine();
        if (getEffects() > 0)
        {
            messageBuilder.append(indent).append(translate("2d20.results.total", getEffects())).appendNewLine();
        }
        if (verbose)
        {
            messageBuilder.append(indent).append("Roll ID: ").append(getUuid()).appendNewLine();
            messageBuilder.append(indent).append(translate("2d20.results.diceResults")).append(" [ ");
            for (SingleResult<Integer> t : getResults())
            {
                messageBuilder.append("( ").append(t.getLabel()).append(" => ");
                messageBuilder.append(t.getValue()).append(") ");
            }
            messageBuilder.append("]\n");
            if (prev != null)
            {
                messageBuilder.append(translate("2d20.results.prevResults")).append("{\n");
                prev.formatResults(messageBuilder, verbose, indentValue + 4);
                messageBuilder.append("}\n");
            }
        }
    }

    public int getEffects()
    {
        return effects;
    }

    public void setEffects(int effects)
    {
        this.effects = effects;
    }

    @Override
    protected String getBundleName()
    {
        return BUNDLE_NAME;
    }

}
