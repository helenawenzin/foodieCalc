import './css/App.css';
import NavBar from './components/Navbar';
import { BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Home from './pages/Home';
import Recipes from './pages/Recipes';
import Costs from './pages/Costs';
import Adding from './pages/Adding';
import AddingRecipe from './pages/AddingRecipe';
import AddingCategory from './pages/AddingCategory';
import AddingIngredient from './pages/AddingIngredient';

     function App() {
        return (
            <>
            <Router>
                <NavBar />
                <Switch>
                    <Route path='/'  exact component={Home}/>
                    <Route path='/costs'  exact component={Costs}/>
                    <Route path='/recipes'  exact component={Recipes}/>
                    <Route path='/adding'  exact component={Adding}/>
                    <Route path='/adding/recipe'  exact component={AddingRecipe}/>
                    <Route path='/adding/category'  exact component={AddingCategory}/>
                    <Route path='/adding/ingredient'  exact component={AddingIngredient}/>
                </Switch>
            </Router>
            </>
            );
     }

export default App;
