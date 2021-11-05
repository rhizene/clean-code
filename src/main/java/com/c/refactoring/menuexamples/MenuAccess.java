package com.c.refactoring.menuexamples;

import static com.c.refactoring.menuexamples.Constants.READ;
import static com.c.refactoring.menuexamples.Constants.WRITE;
import static java.util.Objects.requireNonNull;

import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(List<MenuItem> menuItemsList, Role[] roles) {
        requireNonNull(menuItemsList);
        requireNonNull(roles);
        if(roles.length == 0) {
            return;
        }

        for (MenuItem menuItem : menuItemsList) {
            for (Role role : roles) {
                // constants should be enums
                if(isMenuItemAccessible(role, menuItem, READ)) {
                    setMenuItemAccess(menuItem, READ);
                } else if (isMenuItemAccessible(role, menuItem, WRITE)){
                    setMenuItemAccess(menuItem, WRITE);
                }

            }

        }

    }

    private boolean isMenuItemAccessible(Role role, MenuItem menuItem, String accessConstant) {
        String accessRole;
        switch (accessConstant) {
            case READ:
                accessRole = menuItem.getReadAccessRole();
                break;
            case WRITE:
                accessRole = menuItem.getWriteAccessRole();
                break;
            default:
                return false;
        }

        return role.getName().equals(accessRole);
    }

    private void setMenuItemAccess(MenuItem menuItem, String accessConstant) {
        menuItem.setAccess(accessConstant);
        menuItem.setVisible(true);
    }


}
