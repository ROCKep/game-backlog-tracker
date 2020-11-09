const initialState = {
    game: null,
    gameModalVisible: false,
}

const gameReducers = (state = initialState, action) => {
    switch (action.type) {
        case 'ON_LOAD_GAME':
            return {
                ...state,
                game: action.payload
            };
        case 'OPEN_EDIT_GAME_MODAL':
            return {
                ...state,
                gameModalVisible: true
            };
        case 'CLOSE_EDIT_GAME_MODAL':
            return {
                ...state,
                gameModalVisible: false
            };
        case 'RESET_GAME':
            return initialState;
        default:
            return state;
    }
};

export default gameReducers;