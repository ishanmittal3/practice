package graph;

import java.util.*;

public class Courses {

    // VM ware interview question

    // courses - { 1, 2, 3,4, 5, 6}

//
// prerequisites - {1, 3}
//                 {1, 2}
//                  {3, 5}
    //                 {3, 4}
    //                 {2, 6} {5,6}

// Eng, math, cs ....
// cs -> math, eng

// map: 1 -> {3,2}
//      3 -> {5,4}
//      2 -> {6}
//      5 -> {6}

// listCourses = {4,5,6,3,2,1}
// taken = {4,5,6,3,2,1}
// q = {}

// listCourses = {4,6,2,5,3,1}
// taken = {4,6,2,5,3,1}
// q = {}



    public static List<String> getCourses(List<String> courses, List<String[]> preReqs) {
        List<String> listCourses = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String[] preReq : preReqs) {
            if (map.containsKey(preReq[0])) {
                map.get(preReq[0]).add(preReq[1]);
            } else {
                List<String> deps = new ArrayList<>();
                deps.add(preReq[1]);
                map.put(preReq[0], deps);
            }
        }
        for (String course : courses) {
            boolean base = true;
            for (String[] preReq : preReqs) {
                if (preReq[0].equals(course)) {
                    base = false;
                    break;
                }
            }
            if (base) {
                listCourses.add(course);
            }
        }
        Set<String> taken = new HashSet<>();
        taken.addAll(listCourses);

        Queue<String> q = new ArrayDeque<>();
        q.addAll(map.keySet());
        while (!q.isEmpty()) {
            String front = q.poll();
            if (taken.containsAll(map.get(front))) {
                taken.add(front);
                listCourses.add(front);
            } else {
                q.add(front);
            }
        }

        return listCourses;
    /*
    q.addAll(listCourses);
    while (!q.isEmpty()) {
        String front = q.poll();
        for (String[] preReq : preReqs) {
            if (front.equals(preReq[1])) {
                if (covers(map, taken, preReq[0])) {
                    taken.add(preReq[0]);
                } else {
                    q.add(preReq[0]);
                }
            }
        }
    }
    */
    }
}
