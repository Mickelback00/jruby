/*
 * RubyHashMap.java - No description
 * Created on 10. September 2001, 17:51
 * 
 * Copyright (C) 2001 Jan Arne Petersen, Stefan Matthias Aust
 * Jan Arne Petersen <japetersen@web.de>
 * Stefan Matthias Aust <sma@3plus4.de>
 * 
 * JRuby - http://jruby.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 */

package org.jruby.util;

import java.util.*;

/**
 *
 * @author  jpetersen
 */
public class RubyHashMap extends HashMap implements RubyMap {
    public RubyHashMap() {
        super();
    }

    public RubyHashMap(int initialCapacity) {
        super(initialCapacity);
    }
    
    public RubyHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }
    
    public RubyHashMap(Map t) {
        super(t);
    }
    
    public void foreach(RubyMapMethod method, Object arg) {
        Iterator iter = entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            int ret = method.execute(entry.getKey(), entry.getValue(), arg);
            switch (ret) {
                case RubyMapMethod.CONTINUE: 
                    continue;
                case RubyMapMethod.STOP: 
                    return;
                case RubyMapMethod.DELETE: 
                    iter.remove();
            }
        }
    }
    
    public RubyMap cloneRubyMap() {
        return (RubyMap)clone();
    }
    
}