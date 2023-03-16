package com.jyong.java.Thread;

public class CronTash {


    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(doTask(nums, target));
    }


    public static String doTask(int[] nums, int target) {

        for (int i = 0; i <= nums.length; i++) {
            for (int j = 0; j <= nums.length; j++) {
                int sum = nums[i] + nums[j];
                System.out.println("sum:" + sum);
                if (sum == target) {
                    return i + "---" + j;
                }

            }
        }
        return "error";
    }


}
