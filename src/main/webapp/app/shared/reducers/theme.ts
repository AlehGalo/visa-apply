const ACTION_TYPES = {
  SET_THEME: 'theme/SET_THEME',
};

const initialState = {
  currentTheme: 'default', // Default JHipster theme
};

export type ThemeState = Readonly<typeof initialState>;

// Reducer
export default (state: ThemeState = initialState, action): ThemeState => {
  switch (action.type) {
    case ACTION_TYPES.SET_THEME:
      return {
        ...state,
        currentTheme: action.payload,
      };
    default:
      return state;
  }
};

// Action
export const setTheme = (themeName: string) => ({
  type: ACTION_TYPES.SET_THEME,
  payload: themeName,
});
