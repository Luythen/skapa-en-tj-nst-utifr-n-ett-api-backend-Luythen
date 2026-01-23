package com.github.Luythen.Backend;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class DateSortComparator implements Comparator {

    @Override
    public int compare(Object arg0, Object arg1) {
        
        FavoriteMealModel fModel1 = (FavoriteMealModel) arg0;
        FavoriteMealModel fModel2 = (FavoriteMealModel) arg1;

        if (fModel1.getDate().isBefore(fModel2.getDate())) return 1;
        if (fModel1.getDate().isAfter(fModel2.getDate())) return -1;
        
        return 0;
    }

}
