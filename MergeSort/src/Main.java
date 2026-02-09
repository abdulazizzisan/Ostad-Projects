import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Both lists have values
        ListNode list1 = createList(new int[]{1, 2, 4});
        ListNode list2 = createList(new int[]{1, 3, 4});

        System.out.println("Test Case 1:");
        System.out.print("List 1: ");
        printList(list1);
        System.out.print("List 2: ");
        printList(list2);

        ListNode merged1 = solution.mergeTwoLists(list1, list2);
        System.out.print("Merged: ");
        printList(merged1);
        System.out.println();

        // Test Case 2: One empty list
        ListNode list3 = createList(new int[]{});
        ListNode list4 = createList(new int[]{0});

        System.out.println("Test Case 2:");
        System.out.print("List 3: ");
        printList(list3);
        System.out.print("List 4: ");
        printList(list4);

        ListNode merged2 = solution.mergeTwoLists(list3, list4);
        System.out.print("Merged: ");
        printList(merged2);
        System.out.println();

        // Test Case 3: Different length lists
        ListNode list5 = createList(new int[]{-9, 3});
        ListNode list6 = createList(new int[]{5, 7});

        System.out.println("Test Case 3:");
        System.out.print("List 5: ");
        printList(list5);
        System.out.print("List 6: ");
        printList(list6);

        ListNode merged3 = solution.mergeTwoLists(list5, list6);
        System.out.print("Merged: ");
        printList(merged3);
    }

    // Helper method to create a linked list from an array
    private static ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to print a linked list
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    //      Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

            if (list1 == null && list2 != null) return list2;
            if (list1 != null && list2 == null) return list1;
            if (list1 == null) return null;

            ListNode mergeList = new ListNode();

            ListNode tempNode = mergeList;

            if (list1.val <= list2.val) {
                tempNode.val = list1.val;
                list1 = list1.next;
            } else {
                tempNode.val = list2.val;
                list2 = list2.next;
            }

            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val){
                    tempNode.next = new ListNode(list1.val);
                    list1 = list1.next;
                }else {
                    tempNode.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
                tempNode = tempNode.next;
            }

            while (list1 != null){
                tempNode.next = new ListNode(list1.val);
                list1 = list1.next;
                tempNode = tempNode.next;
            }
            while (list2 != null){
                tempNode.next = new ListNode(list2.val);
                list2 = list2.next;
                tempNode = tempNode.next;
            }

            return mergeList;
        }
    }
}

