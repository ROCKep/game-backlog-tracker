import {combineReducers} from 'redux';
import backlogReducers from "./backlogReducers";
import gameReducers from "./gameReducers";
import searchReducers from "./searchReducers";
import gamesReducers from "./gamesReducers";
import gameModalReducers from "./gameModalReducers";
import userReducers from "./userReducers";

const allReducers = combineReducers({
    backlog: backlogReducers,
    game: gameReducers,
    search: searchReducers,
    games: gamesReducers,
    gameModal: gameModalReducers,
    user: userReducers
});

export default allReducers;