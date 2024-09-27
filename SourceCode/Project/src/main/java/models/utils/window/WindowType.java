package models.utils.window;

/**
     * <p>The Window Types available to the user. The definitions of these are defined in the AWindow class.</p>
     */
    public enum WindowType {
        WINDOWED                (0, "Windowed"),
        WINDOWED_BORDERLESS     (1, "Windowed Borderless"),
        WINDOWED_FULLSCREEN     (2, "Fullscreen Windowed"),
        FULLSCREEN_EXCLUSIVE    (3, "Fullscreen Exclusive");

        private final int type;
        private final String name;

        /**
         * <p>The WindowType constructor.</p>
         * @param type The ordinal of this WindowType, set during the build first time setup.
         * @param name The displayable name of this WindowType.
         */
        WindowType(int type, String name){
            this.type = type;
            this.name = name;
        }

        /**
         * <p>Gets the name of the WindowType</p>
         * @return the WindowType name which is automatically defined by the enum constructor
         */
        public String getName() {
            return name;
        }

    }
