const searchReducers = (state = null, action) => {
    switch (action.type) {
        case 'CHANGE_SEARCH':
            return action.payload;
        case 'RESET_SEARCH':
            return null;
        default:
            return state;
    }
};

export default searchReducers;