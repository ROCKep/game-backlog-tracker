const backlogReducers = (state = [], action) => {
    switch (action.type) {
        case 'ON_LOAD_BACKLOG':
            return action.payload;
        case 'RESET_BACKLOG':
            return [];
        default:
            return state;
    }
};

export default backlogReducers;