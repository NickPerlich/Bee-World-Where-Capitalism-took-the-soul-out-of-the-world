Virtual World Project
CSC 203, Fall '21

Simulation Rules:

There are 8 entities, which act according to the following rules:
1. HOUSE: 
    * Remains static.  Does not animate or complete any actions.  
    * Is the destination for DUDE_FULL entities
2. DUDE_FULL
    * Is a wood chopping entity who has already collected as much wood as they can carry.
    * Navigates to the nearest HOUSE to drop it off.
    * Transforms into a DUDE_NOT_FULL once task is completed
3. DUDE_NOT_FULL
    * navigates to nearest TREE or SAPLING
    * in search of something to chop down
    * will search until it has reached it's resource limit, 
      moving to multiple TREEs or SAPLINGs if necessary.
    * transforms into a DUDE_FULL once it hits it's resource limit
4. OBSTACLE
    * the water - entites cannot move through it
    * the water is not just a background image as it blocks entity paths
5. FAIRY
    * navigates towards the nearest stump and turns it into a SAPLING
6. STUMP
    * a stump does not animate or complete any actions
    * it is a destination for FAIRY entities
7. SAPLING
    * saplings animate and grow into TREE entities once they hit their designated health limit
    * DUDE_NOT_FULL entities can upset a SAPLING entity's growth by chopping it down and depleting it's health
8. TREE
    * animates and has health
    * if it's health is depleted it with transform into a STUMP entity.
