import './App.css';
import NavBar from './components/Navbar';
import { BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Home from './pages/Home';
import Recipes from './pages/Recipes';
import Costs from './pages/Costs';
import Adding from './pages/Adding';

     function App() {
        return (
            <>
            <Router>
                <NavBar />
                <Switch>
                    <Route path='/'  exact component={Home}/>
                    <Route path='/costs'  component={Costs}/>
                    <Route path='/recipes'  component={Recipes}/>
                    <Route path='/adding'  component={Adding}/>
                </Switch>
            </Router>
            </>
            );
     }

export default App;
