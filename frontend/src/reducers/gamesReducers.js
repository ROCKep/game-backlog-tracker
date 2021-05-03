const initialState = {
    games: [],
    gameModalVisible: false
};

const gamesReducers = (state = initialState, action) => {
    switch (action.type) {
        case 'ON_LOAD_GAMES':
            return {
                ...state,
                games: action.payload
            };
        case 'OPEN_NEW_GAME_MODAL':
            return {
                ...state,
                gameModalVisible: true
            };
        case 'CLOSE_NEW_GAME_MODAL':
            return {
                ...state,
                gameModalVisible: false
            };
        case 'RESET_GAMES':
            return initialState;
        default:
            return state;
    }
};

export default gamesReducers;