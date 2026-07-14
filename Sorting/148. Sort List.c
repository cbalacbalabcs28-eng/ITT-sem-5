truct ListNode* merge(struct ListNode* list1, struct ListNode* list2) {
    struct ListNode dummy;
    struct ListNode* tail = &dummy;
    dummy.next = NULL;
    
    while (list1 && list2) {
        if (list1->val < list2->val) {
            tail->next = list1;
            list1 = list1->next;
        } else {
            tail->next = list2;
            list2 = list2->next;
        }
        tail = tail->next;
    }
    
    // Attach the remaining elements of the unfinished list
    tail->next = list1 ? list1 : list2;
    
    return dummy.next;
}

// Helper function to find the middle node of the list using slow/fast pointers
struct ListNode* getMid(struct ListNode* head) {
    struct ListNode* slow = head;
    struct ListNode* fast = head->next;
    
    while (fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;
    }
    return slow;
}

// Main function required by LeetCode
struct ListNode* sortList(struct ListNode* head) {
    if (!head || !head->next) {
        return head;
    }
    struct ListNode* left = head;
    struct ListNode* mid = getMid(head);
    struct ListNode* right = mid->next;
    mid->next = NULL;
    left = sortList(left);
    right = sortList(right);
    return merge(left, right);
}
