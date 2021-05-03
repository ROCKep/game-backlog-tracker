const userReducers = (state = null, action) => {
    switch (action.type) {
        case 'ON_LOAD_USER':
            return action.payload;
        default:
            return state;
    }
};

export default userReducers;