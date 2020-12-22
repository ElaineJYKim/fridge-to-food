import React from 'react';
import Fridge from './Fridge';
import Results from './Results';
import {Grid} from 'semantic-ui-react'

class Service extends React.Component {
    constructor(props) {
        super(props);
        this.state = { 
            ingredients: [],
        };
    }

    handleIngredients(ingredientsList) {
      this.setState({ingredients: ingredientsList})
    }

    render() {
        return (
          <div>
            <Grid columns={2} relaxed='very'>
              <Grid.Column>
                <Fridge 
                  onChange={this.handleIngredients.bind(this)}
                />
              </Grid.Column>
              <Grid.Column>
                <Results 
                  ingredients={this.state.ingredients}
                />
              </Grid.Column>
            </Grid>
          </div>
        );
      }
}
export default Service;