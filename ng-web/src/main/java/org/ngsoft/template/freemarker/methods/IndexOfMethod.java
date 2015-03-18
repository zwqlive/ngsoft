package org.ngsoft.template.freemarker.methods;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * Created by will on 2015-3-4.
 */
public class IndexOfMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        if(list.size() != 2){
            throw new TemplateModelException("illegal argument of \"list\"");
        }
        return new SimpleNumber((list.get(1).toString()).indexOf(list.get(0).toString())).toString();
    }
}
