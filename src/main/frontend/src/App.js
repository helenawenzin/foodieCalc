
//import './App.css';
import React, {Component} from 'react';
import FoodCategories from './components/foodcategories';

class App extends Component {

      state = {
        foodCategories: []
      }

      componentDidMount() {
              fetch('http://localhost:8080/foodcategories')
              .then(res => res.json())
              .then((data) => {
                console.log(data);
                this.setState({ foodCategories: data })
              })
              .catch(console.log)
            }

      render () {
        return (
            <FoodCategories foodCategories={this.state.foodCategories} />
        );
      }
    }

export default App;
