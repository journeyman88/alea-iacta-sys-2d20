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
import java.util.Collections;
import java.util.List;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.roll.GenericResult;

/**
 *
 * @author journeyman
 */
public class M2D20Results extends GenericResult
{
    private final List<Integer> results;
    private int successes = 0;
    private int complication = 0;
    private List<String> usedDice = new ArrayList<>();
    private List<Integer> assistDice = new ArrayList<>();
    private M2D20Results prev;
    
    public M2D20Results(List<Integer> results)
    {
        List<Integer> tmp = new ArrayList<>(results.size());
        tmp.addAll(results);
        this.results = Collections.unmodifiableList(tmp);
    }
    
    private void addSuccesses(int value, Integer ... dice)
    {
        this.addSuccesses(value, Arrays.asList(dice));
    }
    
    public void addSuccess(Integer ... dice)
    {
        addSuccesses(1, dice);
    }
    
    public void addComplication()
    {
        complication ++;
    }
    
    public void addCriticalSuccess(Integer ... dice)
    {
        addSuccesses(2, dice);
    }
    
    private void addSuccesses(int value, Collection<Integer> dice)
    {
        successes += value;
        StringBuilder sb = new StringBuilder("(");
        for (Integer d : dice)
        {
            if (sb.length() >= 2)
            {
                sb.append("+");
            }
            sb.append(d);
        }
        sb.append(")");
        usedDice.add(sb.toString());
    }
    
    public void addSuccess(Collection<Integer> dice)
    {
        addSuccesses(1, dice);
    }
    
    public void addCriticalSuccess(Collection<Integer> dice)
    {
        addSuccesses(2, dice);
    }

    public int getSuccesses()
    {
        return successes;
    }

    public List<String> getUsedDice()
    {
        return usedDice;
    }

    public List<Integer> getResults()
    {
        return results;
    }

    public M2D20Results getPrev()
    {
        return prev;
    }

    public void setPrev(M2D20Results prev)
    {
        this.prev = prev;
    }
    
    @Override
    protected void formatResults(MsgBuilder messageBuilder, boolean verbose, int indentValue)
    {
        String indent = getIndent(indentValue);
        messageBuilder.append(indent).append("Successes: ").append(getSuccesses()).appendNewLine();
        if (getComplication() > 0)
        {
            messageBuilder.append(indent).append("Complications: ").append(getComplication()).appendNewLine();
        }
        if (verbose)
        {
            messageBuilder.append(indent).append("Roll ID: ").append(getUuid()).appendNewLine();
            messageBuilder.append(indent).append("Results: ").append(" [ ");
            for (Integer t : getResults())
            {
                messageBuilder.append(t).append(" ");
            }
            messageBuilder.append("]\n");
            messageBuilder.append(indent).append("Assistance Results: ").append(" [ ");
            for (Integer t : getAssistDice())
            {
                messageBuilder.append(t).append(" ");
            }
            messageBuilder.append("]\n");
            if (prev != null)
            {
                messageBuilder.append("Prev : {\n");
                prev.formatResults(messageBuilder, verbose, indentValue + 4);
                messageBuilder.append("}\n");
            }
        }
    }

    public int getComplication()
    {
        return complication;
    }

    public List<Integer> getAssistDice()
    {
        return assistDice;
    }

    public void setAssistDice(List<Integer> assistDice)
    {
        this.assistDice = assistDice;
    }

}
